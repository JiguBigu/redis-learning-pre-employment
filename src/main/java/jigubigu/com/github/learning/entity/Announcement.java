package jigubigu.com.github.learning.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jigubigu
 * @version 1.0
 * @date 2020/4/27 16:49
 */

/**
 * 通知类
 */
public class Announcement implements Serializable {
    /**
     * 文章编号
     */
    private Long id;
    /**
     * 通知标题
     */
    private String title;
    /**
     * 通知正文
     */
    private String body;
    /**
     * 通知时间
     */
    private Date date;
    private String[] tag;


}
