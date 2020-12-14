package com.ranyk.ssv.backup.service;

/**
 * ClassName:MysqlBackupService<br/>
 * Description:Mysql 数据库备份还原业务接口
 *
 * @author ranyi
 * @date 2020-12-13 10:42
 * Version: V1.0
 */
public interface MysqlBackupService {

    /**
     * 备份数据库接口
     *
     * @param host 地址
     * @param userName 用户名
     * @param password 密码
     * @param backupFolderPath 备份文件夹路径
     * @param fileName  备份文件名
     * @param database 备份数据库
     * @return 返回操作结果
     * @throws Exception 抛出 Exception
     */
    boolean backup(String host, String userName, String password, String backupFolderPath, String fileName, String database) throws Exception;

    /**
     * 还原数据库
     *
     * @param restoreFilePath 还原文件路径
     * @param host 地址
     * @param userName 用户名
     * @param password 密码
     * @param database 还原数据库
     * @return 返回操作结果
     * @throws Exception 抛出 Exception
     */
    boolean restore(String restoreFilePath, String host, String userName, String password, String database) throws  Exception;

}
