package com.ssm.framework.task.user;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 测试Spring-Task (还有另一种 Spring-Quartz未配置)
 * UserTask.
 *
 * @author zax
 */
@Component("userTask")
public class UserTask {
    @Scheduled(cron = "1 * * * * *")
    private void userTaskTest() {
        System.out.println("================测试Task是否能够执行============");
    }

}
