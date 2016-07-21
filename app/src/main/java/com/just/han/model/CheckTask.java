package com.just.han.model;

import java.io.Serializable;

/**
 * Created by Just on 2016/7/19.
 */
public class CheckTask implements Serializable{
    //巡检任务id
    public String TaskId;
    /// 任务编号
    public String TaskCode;
    /// 巡检任务名称
    public String TaskName;
    /// 检查任务类型
    public String TaskType;
    /// 检查任务类型名称
    public String TaskTypeName;
    ///
    public String TaskCycle;
    /// 注意事项
    public String TaskAttention;
    /// 路线id
    public String RouteId;
    /// 路线名称
    public String RouteName;
    /// 班组id
    public String TeamId;
    /// 班组名称
    public String TeamName;
    /// 开始时间
    public String StartTime;
    /// 结束时间
    public String EndTime;
    /// 巡检周期
    public String CheckCycle;
    /// 检查班次
    public String CheckClass;
    /// 检查状态
    public String TaskStatus;
    /// 部门id
    public String DeptId;

    public String OrganiseCode;
    /// 部门名称
    public String DeptName;
    /// 登记人id
    public String CreateUserId;
    /// 登记人姓名
    public String CreateUserName;
    /// 登记部门
    public String CreateDeptId;
    /// 登记部门名称
    public String CreateDeptName;
    /// 登记时间
    public String CreateTime;
    /// 序号
    public int ShowOrder;
}
