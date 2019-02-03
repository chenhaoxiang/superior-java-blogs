package com.huijava.superiorjavablogs.mapper;

import com.huijava.superiorjavablogs.BaseTest;
import com.huijava.superiorjavablogs.entity.AliRedPacketConfig;
import com.huijava.superiorjavablogs.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class AliRedPacketConfigMapperExtTest extends BaseTest {

    @Autowired
    private AliRedPacketConfigMapper aliRedPacketConfigMapper;

    /**
     * 随机分配红包算法
     */
    @Test
    public void addAliRedPacketConfig() {
        double[] sum = new double[]{264.00, 66.00};
        int[] size = new int[]{20, 80};
        List<AliRedPacketConfig> aliRedPacketConfigs = new ArrayList<>(64);
        for (int i = 0; i < size.length; i++) {
            double[] rs = divide(sum[i], size[i], 0.01);
            for (double each : rs) {
                System.out.format("%.2f\n", each);
                AliRedPacketConfig aliRedPacketConfig = new AliRedPacketConfig();
                String password = "谙忆祝您新年快乐" + StringUtils.getRedPaxketRandomString(12);
                aliRedPacketConfig.setPassword(password);
                //会有一定误差，这里不考虑修正，接受范围内
                aliRedPacketConfig.setMoney(new BigDecimal(each).setScale(2, RoundingMode.HALF_UP));
                aliRedPacketConfig.setStatus((byte) 0);
                aliRedPacketConfig.setType((byte) (i + 1));
                log.info("aliRedPacketConfig={}", aliRedPacketConfig);
                aliRedPacketConfigs.add(aliRedPacketConfig);
            }
        }
        log.info("===================");
        log.info("aliRedPacketConfigs.size={}", aliRedPacketConfigs.size());
        double sumd = 0.0;
        //随机插入数据库
        Collections.shuffle(aliRedPacketConfigs);
        for (AliRedPacketConfig aliRedPacketConfig : aliRedPacketConfigs) {
            aliRedPacketConfigMapper.insertSelective(aliRedPacketConfig);
            sumd += aliRedPacketConfig.getMoney().doubleValue();
        }
        log.info("sumd={}", sumd);


    }

    /**
     * 分配红包算法
     *
     * @param totalMoney 总金额
     * @param size       分配数量
     * @param minMoney   最小金额
     * @return
     */
    private double[] divide(double totalMoney, int size, double minMoney) {
        double[] each = new double[size];
        for (int i = 0; i < size - 1; i++) {
            //计算最大安全值，保证不会超出  。如果安全值越大，价格波动越大  .平均值即可
            double maxSafeMoney = (totalMoney - (size - 1 - i) * minMoney) / (size - 1 - i);
            //随机算出最小值，保证不低于最小值
            each[i] = Math.random() * (maxSafeMoney - minMoney) + minMoney;
            //计算当前剩下的钱
            totalMoney = totalMoney - each[i];
        }
        each[size - 1] = totalMoney;
        return each;
    }

}