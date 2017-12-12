/*
 * Copyright (c) 2017. CK. All rights reserved.
 */

package com.tyj.jhpt.server.body;

import com.tyj.jhpt.server.body.dto.RanLiaoDianChiDto;
import com.tyj.jhpt.server.message.MessageBean;
import com.tyj.jhpt.server.message.type.RealTimeMessage;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

import static com.tyj.jhpt.server.body.ThreeBody.DataEnum.dcStatus;
import static com.tyj.jhpt.server.body.ThreeBody.DataEnum.dianliu;
import static com.tyj.jhpt.server.body.ThreeBody.DataEnum.dianya;
import static com.tyj.jhpt.server.body.ThreeBody.DataEnum.maxConcentration;
import static com.tyj.jhpt.server.body.ThreeBody.DataEnum.maxConcentrationNo;
import static com.tyj.jhpt.server.body.ThreeBody.DataEnum.maxPressure;
import static com.tyj.jhpt.server.body.ThreeBody.DataEnum.maxPressureNo;
import static com.tyj.jhpt.server.body.ThreeBody.DataEnum.maxTanzhenNo;
import static com.tyj.jhpt.server.body.ThreeBody.DataEnum.maxTemperature;
import static com.tyj.jhpt.server.body.ThreeBody.DataEnum.tanzhenTotal;
import static com.tyj.jhpt.server.body.ThreeBody.DataEnum.xiaohaolv;

/**
 * 燃料电池数据
 *
 * @author: CK
 * @date: 2017/12/8
 */
@Component
public class ThreeBody extends AbstractBody<RanLiaoDianChiDto> {
    public ThreeBody() {
        super(RealTimeMessage.RANLIAO_DIANCHI.getCode());
    }

    public RanLiaoDianChiDto deal(MessageBean mb) {
        RanLiaoDianChiDto dto = new RanLiaoDianChiDto();
        byte[] content = mb.getContent();
        int offset = 0;
        // 燃料电池电压
        byte[] bytes = new byte[dianya.length];
        System.arraycopy(content, offset, bytes, 0, dianya.length);
        offset += dianya.length;
        BigInteger bigInteger = new BigInteger(bytes);
        int dianya = bigInteger.intValue();
        dto.setDianya(dianya);

        // 燃料电池电流
        bytes = new byte[dianliu.length];
        System.arraycopy(content, offset, bytes, 0, dianliu.length);
        offset += dianliu.length;
        bigInteger = new BigInteger(bytes);
        int dianliu = bigInteger.intValue();
        dto.setDianliu(dianliu);

        // 燃料消耗率
        bytes = new byte[xiaohaolv.length];
        System.arraycopy(content, offset, bytes, 0, xiaohaolv.length);
        offset += xiaohaolv.length;
        bigInteger = new BigInteger(bytes);
        int xiaohaolv = bigInteger.intValue();
        dto.setXiaohaolv(xiaohaolv);

        // 燃料电池温度探针总数
        bytes = new byte[tanzhenTotal.length];
        System.arraycopy(content, offset, bytes, 0, tanzhenTotal.length);
        offset += tanzhenTotal.length;
        bigInteger = new BigInteger(bytes);
        int tanzhenTotal = bigInteger.intValue();
        dto.setTanzhenTotal(tanzhenTotal);

        // 探针温度值
        bytes = new byte[tanzhenTotal];
        System.arraycopy(content, offset, bytes, 0, tanzhenTotal);
        offset += tanzhenTotal;
        dto.setTemperature(bytes);

        // 氢系统中最高温度
        bytes = new byte[maxTemperature.length];
        System.arraycopy(content, offset, bytes, 0, maxTemperature.length);
        offset += maxTemperature.length;
        bigInteger = new BigInteger(bytes);
        int maxTemperature = bigInteger.intValue();
        dto.setMaxTemperature(maxTemperature);

        // 氢系统中最高温度探针代号
        dto.setMaxTanzhenNo(content[offset + maxTanzhenNo.length]);
        offset += maxTanzhenNo.length;

        // 氢气最高浓度
        bytes = new byte[maxConcentration.length];
        System.arraycopy(content, offset, bytes, 0, maxConcentration.length);
        offset += maxConcentration.length;
        bigInteger = new BigInteger(bytes);
        int maxConcentration = bigInteger.intValue();
        dto.setMaxConcentration(maxConcentration);

        // 氢气最高浓度传感器代号
        dto.setMaxConcentrationNo(content[offset + maxConcentrationNo.length]);
        offset += maxConcentrationNo.length;

        // 氢气最高压力
        bytes = new byte[maxPressure.length];
        System.arraycopy(content, offset, bytes, 0, maxPressure.length);
        offset += maxPressure.length;
        bigInteger = new BigInteger(bytes);
        int maxPressure = bigInteger.intValue();
        dto.setMaxPressure(maxPressure);

        // 氢气最高压力传感器代号
        dto.setMaxPressureNo(content[offset + maxPressureNo.length]);
        offset += maxPressureNo.length;

        // 高压DC/DC状态
        dto.setDcStatus(content[offset + dcStatus.length]);

        return dto;
    }

    public static enum DataEnum {
        dianya(4, "燃料电池电压"),
        dianliu(4, "燃料电池电流"),
        xiaohaolv(4, "燃料消耗率"),
        tanzhenTotal(4, "燃料电池温度探针总数"),
        maxTemperature(4, "氢系统中最高温度"),
        maxTanzhenNo(1, "氢系统中最高温度探针代号"),
        maxConcentration(4, "氢气最高浓度"),
        maxConcentrationNo(1, "氢气最高浓度传感器代号"),
        maxPressure(4, "氢气最高压力"),
        maxPressureNo(1, "氢气最高压力传感器代号"),
        dcStatus(1, "高压DC/DC状态"),
        ;
        private int length;
        private String desc;
        DataEnum(int length, String desc) {
            this.length = length;
            this.desc = desc;
        }
    }
}
