package com.ranyk.ssv.backup.utils;

import com.ranyk.ssv.backup.constent.BackUpConstant;
import com.ranyk.ssv.common.utils.StringUtils;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * ClassName:MysqlBackupRestoreUtils
 * Description:
 *
 * @author ranyi
 * @date 2020-12-14 14:21
 * Version: V1.0
 */
@Log4j2
public class MysqlBackupRestoreUtils {

    /**
     * 数据库备份
     *
     * @param host             数据库地址
     * @param userName         数据库用户名
     * @param password         数据库密码
     * @param backupFolderPath 备份文件夹路径
     * @param fileName         备份文件名
     * @param database         需备份的数据库名称
     * @return 返回备份结果
     */
    public static boolean backup(String host, String userName, String password, String backupFolderPath, String fileName, String database) throws IOException, InterruptedException {
        // 1. 判断传入的参数是否为空
        if (parameterJudgment(host, userName, password, backupFolderPath, fileName, database)) {
            log.error("备份时传入的各个参数存在空值的情况,不予进行数据备份!");
            return false;
        }
        // 2. 创建备份文件夹对象,用于下一步的文件夹是否需要创建判断
        File folder = new File(backupFolderPath);
        // 3. 通过上一步的文件夹对象,判断备份的文件夹是否存在,不存在则需先创建数据备份的文件夹
        if (!folder.exists()) {
            // 3.1. 不存在文件夹,则创建有关的文件夹
            if (!folder.mkdirs()) {
                log.error("创建备份文件夹失败,检查有关路径及其父路径是否存在!");
            }
        }
        // 4. 判断备份文件夹路径是否存在路径分割符,用于之后的备份文件路径的拼接
        if (!backupFolderPath.endsWith(File.separator)
                && !backupFolderPath.endsWith(BackUpConstant.UNIX_PATH_SEPARATOR.getValue())) {
            // 4.1. 当备份文件夹路径没有路径分割符结尾时,在其末尾追加路径分割符
            backupFolderPath = backupFolderPath + File.separator;
        }
        // 5. 拼接数据备份的文件路径
        String backupFilePath = backupFolderPath + fileName;
        // 6. 生成 MySQL 数据库的备份命令字符串
        StringBuilder commandBuilder = new StringBuilder();
        commandBuilder.append("mysqldump --opt ")
                .append(" --add-drop-database ")
                .append(" --add-drop-table ");
        commandBuilder.append(" -h").append(host)
                .append(" -u").append(userName)
                .append(" -p").append(password);
        commandBuilder.append(" --result-file=").append(backupFilePath)
                .append(" --default-character-set=utf8 ").append(database);
        // 7. 调用外部执行 exe 文件的 Java API 方法
        Process exec = Runtime.getRuntime().exec(getCommandStr(commandBuilder));
        // 8. 等待处理完成,当 waitFor() 结果为 0 时就表示线程正常终止
        if (exec.waitFor() == 0) {
            log.error("数据已被分到 " + backupFilePath + " 文件中.");
            // 8.1. 线程执行完成并正常终止,返回备份成功
            return true;
        }
        //9. 线程异常或为正常执行,返回备份失败
        return false;
    }

    /**
     * 获取执行备份的字符数组
     *
     * @param commandBuilder 数据备份的命令字符串
     * @return 返回组装生成的字符数组
     */
    private static String[] getCommandStr(StringBuilder commandBuilder) {
        // 1. 获取操作系统名
        String os = System.getProperty("os.name");
        // 2. 定义初始 shell
        String shell = "/bin/bash";
        // 3. 定义初始 c
        String c = "-c";
        // 4. 判断系统是否是 win 系统,是则需更换 shell 和 c 的值
        if (os.toLowerCase().startsWith(BackUpConstant.BACK_UP_WIN.getValue())) {
            // 4.1. 更换 shell 值
            shell = "cmd";
            //4.2. 更换 c 值
            c = "/c";
        }
        // 5. 返回组装的命令数组
        return new String[]{shell,c,commandBuilder.toString()};
    }


    /**
     * 数据库还原
     *
     * @param restoreFilePath 还原数据文件路径
     * @param host 还原数据库的地址
     * @param userName 还原数据库用户名
     * @param password 还原数据库密码
     * @param database 还原数据库名
     * @return 返回还原结果
     */
    public static boolean restore(String restoreFilePath, String host, String userName, String password, String database) throws IOException, InterruptedException {
        // 1. 判断传入的参数是否存在空值,存在则不予进行数据还原操作
        if (parameterJudgment(host, userName, password, restoreFilePath, database)) {
            log.error("还原时传入的各个参数存在空值的情况,不予进行数据还原!");
            return false;
        }
        // 2. 获取传入的还原数据文件的File对象
        File restoreFile = new File(restoreFilePath);
        // 3. 判断传入的还原数据对象是文件还是文件夹
        if (restoreFile.isDirectory()) {
            // 3.1. 传入的还原文件路径为文件夹,则遍历此文件夹下的文件第一个存在的文件且以 .sql 结尾的文件作为还原的数据的备份数据文件
            for (File file : restoreFile.listFiles()) {
                // 3.1.1. 判断当前循环的 File 对象是否为之前数据备份的文件
                if (file.exists() && file.getPath().endsWith(".sql")) {
                    restoreFilePath = file.getAbsolutePath();
                    break;
                }
            }
        }
        // 4. 创建数据还原命令字符串,为下一步的还原命令做准备
        StringBuilder restoreBuilder = new StringBuilder();
        restoreBuilder.append("mysql -h").append(host).append(" -u").append(userName).append(" -p").append(password)
                .append(" ").append(database).append(" < ").append(restoreFilePath);
        // 5. 调用外部执行 exe 文件的 Java API 方法
        Process exec = Runtime.getRuntime().exec(getCommandStr(restoreBuilder));
        if (exec.waitFor() == 0){
            log.error("数据已从 " + restoreFilePath + " 还原到 " + database +"  数据库中!");
            return true;
        }
        return false;
    }

    /**
     * 传入参数判定,当传入的参数中存在一个参数为空则返回 true;都不为空的时候返回 false;
     *
     * @param params 需要判断的参数的值
     * @return 存在空参数则返回 true; 否则返回 false;
     */
    private static Boolean parameterJudgment(String... params) {
        // 1. 判断传入的参数是否存在值,不存在则返回true
        if (Arrays.asList(params).size() <= 0) {
            log.error("参数判定时,传入参数为空");
            return true;
        }
        // 2. 循环遍历传入的参数值,判断传入的参数是否为空,传入的参数只要有一个存在空,则返回true
        for (String param : params) {
            // 2.1. 判断当前循环值是否为空
            if (StringUtils.isBlank(param)) {
                log.error("传入的参数为空!");
                return true;
            }
        }
        // 3. 所有参数均不为空,返回false
        return false;

    }

}
