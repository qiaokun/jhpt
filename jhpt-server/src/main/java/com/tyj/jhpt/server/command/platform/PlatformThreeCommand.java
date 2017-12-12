/*
 * Copyright (c) 2017. CK. All rights reserved.
 */

package com.tyj.jhpt.server.command.platform;

import com.tyj.jhpt.server.message.PlatformCommandEnum;
import com.tyj.jhpt.server.handler.DeviceManagerServerHandler;
import com.tyj.jhpt.server.message.MessageBean;
import com.tyj.jhpt.server.util.DeviceMsgUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.tyj.jhpt.server.command.platform.PlatformThreeCommand.DataEnum.COMMAND_ID;
import static com.tyj.jhpt.server.command.platform.PlatformThreeCommand.DataEnum.TIME;

/**
 * 车载终端控制
 *
 * @author: CK
 * @date: 2017/12/8
 */
@Component
public class PlatformThreeCommand extends PlatformAbstractCommand {
    public PlatformThreeCommand() {
        super(PlatformCommandEnum.CAR_TERMINAL_CONTROL.getType());
    }

    public void deal(DeviceManagerServerHandler handler, MessageBean mb) {
        // 参数设置时间
        byte[] content = mb.getContent();
        Date time = DeviceMsgUtils.resolveTime(content, TIME.length);
        int offset = TIME.length;

        // 命令ID
        byte commandId = content[offset + COMMAND_ID.length];
        offset += COMMAND_ID.length;

    }

    public static enum DataEnum {
        TIME(6, "时间"),
        COMMAND_ID(1, "命令ID"),
        ;
        private int length;
        private String desc;
        DataEnum(int length, String desc) {
            this.length = length;
            this.desc = desc;
        }
    }
}
