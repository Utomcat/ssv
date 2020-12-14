package com.ranyk.ssv.backup.service.impl;

import com.ranyk.ssv.backup.service.MysqlBackupService;
import com.ranyk.ssv.backup.utils.MysqlBackupRestoreUtils;
import org.springframework.stereotype.Service;

/**
 * ClassName:MysqlBackupServiceImpl<br/>
 * Description:Mysql 数据库备份还原业务实现类
 *
 * @author ranyi
 * @date 2020-12-13 11:08
 * Version: V1.0
 */
@Service
public class MysqlBackupServiceImpl implements MysqlBackupService {

    /**
     * 备份数据库接口
     *
     * @param host             地址
     * @param userName         用户名
     * @param password         密码
     * @param backupFolderPath 备份文件夹路径
     * @param fileName         备份文件名
     * @param database         备份数据库
     * @return 返回操作结果
     * @throws Exception 抛出 Exception
     */
    @Override
    public boolean backup(String host, String userName, String password, String backupFolderPath, String fileName, String database) throws Exception {
        return MysqlBackupRestoreUtils.backup(host, userName, password, backupFolderPath, fileName, database);
    }

    /**
     * 还原数据库
     *
     * @param restoreFilePath 还原文件路径
     * @param host            地址
     * @param userName        用户名
     * @param password        密码
     * @param database        还原数据库
     * @return 返回操作结果
     * @throws Exception 抛出 Exception
     */
    @Override
    public boolean restore(String restoreFilePath, String host, String userName, String password, String database) throws Exception {
        return MysqlBackupRestoreUtils.restore(restoreFilePath, host,userName, password, database);
    }

}
