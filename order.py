import sys
import numpy as np
import requests
import json

class order:###组织预约
    def __init__(self,order_id,user_id,time_id):
        self.order_id=order_id
        self.user_id=user_id
        self.time_id=time_id

def request_get(url, param):##调用外部java接口
    fails = 0
    while True:
        try:
            if fails >= 20:
                break
            ret = requests.get(url=url, params=param, timeout=10)
            if ret.status_code == 200:
                text = json.loads(ret.text)
            else:
                continue
        except:
            fails += 1
            print('网络连接出现问题, 正在尝试再次请求: ', fails)
        else:
            break
    return text
###java借口链接
url_listAllOrderSpotInfo="http://47.104.248.28:8080/order/listAllOrderSpotInfo"#获得可预约地点的全部信息{}
url_agreeOrder="http://47.104.248.28:8080/order/agreeOrder"#同意某个预约{orderId}
url_cancelOrder="http://47.104.248.28:8080/order/cancelOrder"#取消某个预约{orderId}
url_disagreeOrder="http://47.104.248.28:8080/order/disagreeOrder"#拒绝某个预约{orderId}
url_listOrdersOfLastThreeDays="http://47.104.248.28:8080/order/listOrdersOfLastThreeDays"#列出近三天某个地点的饿预约情况，orderStatus=1代表同意，orderStatus=2代表拒绝{orderStatus,spotId}
url_listTomorrowPendingOrders="http://47.104.248.28:8080/order/listTomorrowPendingOrders"#列出明天的待处理预约{spotId}

accept=1##每个接受预约降低的优先级数目
reject=1##每个拒绝预约的增加的优先级数目

if __name__=='__main__':
    orderSpotInfo=request_get(url_listAllOrderSpotInfo,{})##获得所有可预约地点的信息
    spot_Id=[]##可预约地点id
    spot_time={}##每个可预约地点的时间段数目
    spot_time_dict={}##每个可预约地点时间段编号字典
    spot_time_people={}##每个可预约地点每个时间段内可容纳人数
    for i in orderSpotInfo:
        spot_Id.append(i['spotId'])
        spot_time[i['spotId']]=len(i['spotOrderTimeList'])
        spot_time_dict[i['spotId']]=[]
        for j in i['spotOrderTimeList']:
            spot_time_dict[i['spotId']].append(j['spotOrderTimeId'])
        spot_time_people[i['spotId']]=i['suggestedPeople']
    # print(spot_Id)
    # print(spot_time)
    # print(spot_time_dict)
    # print(spot_time_people)
    for i in spot_Id:
        # print(i)
        order_pending=[]#待处理
        order_accept=[]#接受
        order_reject=[]#拒绝
        tomorrowPendingOrders=request_get(url_listTomorrowPendingOrders,{'spotId':i})##获得某种资源明天的所有待处理预约
        # print('orderpending length',len(tomorrowPendingOrders))
        for j in tomorrowPendingOrders:##获取待处理预约
            assert(j['orderTime']['spotId']==i)
            assert(j['orderStatus']==0)
            timeId=j['orderTime']['spotOrderTimeId']
            order_pending.append(order(j['orderId'],j['userId'],spot_time_dict[i].index(timeId)))
        # print(order_pending)
        if len(order_pending)==0:
            continue
        acceptThreeDays=request_get(url_listOrdersOfLastThreeDays,{'orderStatus':1,'spotId':i})##获得某种资源最近三天全部的接受预约
        for j in acceptThreeDays:
            assert(j['orderTime']['spotId']==i)
            assert(j['orderStatus']==1)
            timeId=j['orderTime']['spotOrderTimeId']
            order_accept.append(order(j['orderId'],j['userId'],spot_time_dict[i].index(timeId)))
        rejectThreeDays=request_get(url_listOrdersOfLastThreeDays,{'orderStatus':2,'spotId':i})##获得某种资源最近三天全部的拒绝预约
        # print(order_accept)
        for j in rejectThreeDays:
            assert(j['orderTime']['spotId']==i)
            assert[j['orderStatus']==2]
            timeId=j['orderTime']['spotOrderTimeId']
            order_reject.append(order(j['orderId'],j['userId'],spot_time_dict[i].index(timeId)))
        # print(order_reject)
        time=spot_time[i]##该资源可以预约的时间段的数目
        people=spot_time_people[i]##该资源每个时间段内可承载的最大人数
        tmp_user={}###user id：对应order列表
        tmp_time=[[] for _ in range(time)]###每个时间段order的列表
        tmp_priority={}##字典，user id：优先级
        for j in order_pending:##将order按照时间段划分，按照用户id划分
            tmp_time[j.time_id].append(j)
            if j.user_id not in tmp_user:
                tmp_user[j.user_id]=[]
            tmp_user[j.user_id].append(order)
        for user_id in tmp_user:##根据今天三天该用户对该资源的预约申请情况给每个用户确定优先级，得到同意越多优先级越低，得到拒绝越多优先级越高
            priority=0
            for j in order_accept:
                if j.user_id==user_id:
                    priority=priority-accept
            for j in order_reject:
                if j.user_id==user_id:
                    priority=priority+reject
            tmp_priority[user_id]=priority
        # print(tmp_priority)
        while True:
            if len(tmp_time)==0:
                break
            order_len=[len(a) for a in tmp_time]
            prior_handle=order_len.index(min(order_len))##采用贪心策略每次处理预约数最少的时间段，减少不同用户的时间冲突，尽可能满足更多人的需求
            if order_len[prior_handle]<=people:##当人数少于该资源人数上限时全部同意预约，被同意的用户对这个资源的其他预约全部取消
                for j in tmp_time[prior_handle]:
                    accept_order=j.order_id
                    request_get(url_agreeOrder,{"orderId":accept_order})
                    # print('accept1:',accept_order)
                    for k in range(len(tmp_time)):
                        if k!=prior_handle:
                            for kk in tmp_time[k]:
                                if kk.user_id==j.user_id:
                                    cancel_order=kk.order_id
                                    request_get(url_cancelOrder,{"orderId":cancel_order})
                                    tmp_time[k].remove(kk)
            else:##根据优先级满足用户需求
                priority_list=[]
                for j in tmp_time[prior_handle]:
                    priority_list.append(tmp_priority[j.user_id])
                priority_list=list(np.argsort(-np.array(priority_list)))
                accept_list=priority_list[0:people]
                reject_list=priority_list[people:]
                for j in accept_list:##同意accept列表里的预约并且取消对应用户的其他预约。
                    accept_order=tmp_time[prior_handle][j].order_id
                    request_get(url_agreeOrder,{"orderId":accept_order})
                    accept_user=tmp_time[prior_handle][j].user_id
                    # print('accept2:',accept_order)
                    for k in range(len(tmp_time)):
                        if k!=prior_handle:
                            for kk in tmp_time[k]:
                                if kk.user_id==accept_user:
                                    cancel_order=kk.order_id
                                    request_get(url_cancelOrder,{"orderId":cancel_order})
                                    tmp_time[k].remove(kk)
                    del tmp_user[accept_user]
                for j in reject_list:##拒绝reject列表里的预约，当前用户如果只有剩下一个预约未处理则拒绝该预约，否则取消该预约，保证该用户一天对某个地点最多获得一个拒绝，维护了优先级的公平性。
                    reject_order=tmp_time[prior_handle][j].order_id
                    reject_user=tmp_time[prior_handle][j].user_id
                    if len(tmp_user[reject_user])==1:
                        request_get(url_disagreeOrder,{"orderId":reject_order})
                        del tmp_user[reject_user]
                    else:
                        request_get(url_cancelOrder,{"orderId":reject_order})
                        tmp_user[reject_user].remove(tmp_time[prior_handle][j])
            del tmp_time[prior_handle]
    