package com.utils.work.jxkp;

public class Constants {
    // 计划进度状态
    public static class PLAN_STATE {
        public static final int PREPIRE = 0; // 未开始
        public static final int DOING = 1; // 进行中
        public static final int COMPLETE = 2; // 已结束
    }

    // 个人考评计划状态
    public static class STAFF_STATE {
        public static final int SELF = 1; // 自评阶段
        public static final int FIRST = 2; // 一级主管评价阶段
        public static final int SECOND = 3; // 二级主管评价阶段
        public static final int FINAL = 4; // 最终评审阶段
        public static final int COMPLETE = 0; // 已结束或已完成
    }

    // 用户用户状态
    public static class USER_STATE {
        public static final int ALIVE = 1;
        public static final int BLOCKED = 0;
    }

    // 用户角色？
    public static class USER_ROLE {
//        区别人力 评价
    }

    // 岗位大类
    public static class POSITON_CLASS {
        public static final int DEVELOP = 0;
        public static final int FUNCTION = 1;
        public static final int SALE = 2;
    }
}
