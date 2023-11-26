//package com.lizzy.demo.utils;
//
//import org.springframework.stereotype.Component;
//
//@Component
//public class SnowFlake {
//    //起始的时间戳
//    private final static long START_STMP = 1609459200000L;//2021-01-01 00:00:00
//
//    //每一部分占用的位数
//    private final static long SEQUENCE_BIT = 12;//序列号占用的位数
//    private final static long MACHINE_BIT = 5;//机器标识占用的位数
//    private final static long DATACENTER_BIT = 5;//数据中心占用的位数
//
//    //每一部分的最大值
//    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
//    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
//    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);
//
//
//    //每一部分向左的位移
//    private final static long MACHINE_LEFT = SEQUENCE_BIT;
//    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
//    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;
//
//    private long datacenterId =1;//数据中心
//    private long machineId =1;//机器标识
//    private long sequence =0L;//序列号
//    private long laststmp =-1L;//上一次时间戳
//
//    public SnowFlake() {
//    }
//
//    public SnowFlake(long datacenterId, long machineId) {
//        if(datacenterId>MAX_DATACENTER_NUM||datacenterId<0){
//            throw new IllegalArgumentException("datacenterID can't be greater than MAX_DATACENTER_NUM or less than 0");
//        }
//        if(machineId>MAX_MACHINE_NUM||machineId<0){
//            throw new IllegalArgumentException("machineID can't be greater than MAX_MACHINE_NUM or less than 0");
//        }
//        this.datacenterId = datacenterId;
//        this.machineId = machineId;
//    }
//
//
//    //获得下一个ID (该方法是线程安全的)
//    public synchronized long nextId() {
//        long timestamp = timeGen();
//
//        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
//        if (timestamp < laststmp) {
//            throw new RuntimeException(
//                    String.format(
//                            "Clock moved backwards.  Refusing to generate id for %d milliseconds",
//                            (laststmp - timestamp)));
//        }
//
//        // 如果是同一时间生成的，则进行毫秒内序列
//        if (laststmp == timestamp) {
//            sequence = (sequence + 1) & MAX_SEQUENCE;
//            // 毫秒内序列溢出
//            if (sequence == 0) {
//                // 阻塞到下一个毫秒,获得新的时间戳
//                timestamp = tilNextMillis(laststmp);
//            }
//        }
//        // 时间戳改变，毫秒内序列重置
//        else {
//            sequence = 0L;
//        }
//
//        // 上次生成ID的时间截
//        laststmp = timestamp;
//
//        // 移位并通过或运算拼到一起组成64位的ID
//        return ((timestamp - START_STMP) << TIMESTMP_LEFT) //
//                | (datacenterId << DATACENTER_LEFT) //
//                | (machineId << MACHINE_LEFT) //
//                | sequence;
//    }
//
//    /**
//     * 阻塞到下一个毫秒，直到获得新的时间戳
//     *
//     * @param lastTimestamp
//     *            上次生成ID的时间截
//     * @return 当前时间戳
//     */
//    protected long tilNextMillis(long lastTimestamp) {
//        long timestamp = timeGen();
//        while (timestamp <= lastTimestamp) {
//            timestamp = timeGen();
//        }
//        return timestamp;
//    }
//
//    /**
//     * 返回以毫秒为单位的当前时间
//     *
//     * @return 当前时间(毫秒)
//     */
//    protected long timeGen() {
//        return System.currentTimeMillis();
//    }
//}
