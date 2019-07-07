package com.xie.java.datasource.interceptor;

import com.xie.java.datasource.RouteContextManager;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 *
 * @author xieyang
 * @date 19/7/4
 */
public class PreTransactionInterceptor extends TransactionInterceptor {
    public final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        boolean transaction = true;
        int increase = RouteContextManager.increase(transaction);
        if (increase == 1) {
            logger.debug("进入事务拦截器");
        }
        String databaseId = RouteContextManager.getDatabaseId(invocation.getMethod());
        if(databaseId == null){
            databaseId = RouteContextManager.getDefaultDatabaseId();
        }
        boolean master = RouteContextManager.isMaster(databaseId);
        if(master){
            RouteContextManager.setCurrentDatabaseId(databaseId,transaction);
        }else {
            //自动切到主库
            String masterId = RouteContextManager.getMasterId(databaseId);
            RouteContextManager.setCurrentDatabaseId(masterId,transaction);
            logger.debug("有事务从库[{}]切到主库[{}]",databaseId,masterId);
        }

        try {
            return super.invoke(invocation);
        } finally {
            int decrease = RouteContextManager.decrease(transaction);
            RouteContextManager.setCurrentDatabaseId(null,transaction);
            if (decrease == 0) {
                logger.debug("退出事务");
            }
        }
    }

//    @Override
//    protected PlatformTransactionManager determineTransactionManager(TransactionAttribute txAttr) {
//        String manangerName =TRANSACTION_MANAGER_PREFIX+RouteContextManager.currentDatabaseId();
//        Object bean = getBeanFactory().getBean(manangerName);
//        if(bean != null){
//            return (PlatformTransactionManager) bean;
//        }
//        return  super.determineTransactionManager(txAttr);
//    }
}
