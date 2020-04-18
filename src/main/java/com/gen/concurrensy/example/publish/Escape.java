package com.gen.concurrensy.example.publish;

import com.gen.concurrensy.annotation.NotRecommend;
import com.gen.concurrensy.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 对象溢出
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    /**
     * 构造函数中初始化了内部类，内部类访问了外部类的属性（Escape.this.thisCanBeEscape）
     * 有可能在外部类还未完全初始化之前就访问到对象，不安全
     */
    public Escape () {
        new InnerClass();
    }

    private class InnerClass {

        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
