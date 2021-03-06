package com.imen.mybatisplus.extension.strategy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imen.mybatisplus.extension.config.QueryCondition;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wfee
 */
public class WhereZeroParamStrategy extends AbstractQueryStrategy {
    public WhereZeroParamStrategy(QueryCondition queryCondition, QueryWrapper<?> queryWrapper, String column, Object[] values) {
        super(queryCondition, queryWrapper, column, values);
    }

    @Override
    protected Object[] getMapperColumns() {
        return new String[]{getColumn()};
    }

    @Override
    protected Method getMethod() throws NoSuchMethodException {
        return getQueryWrapper().getClass().getMethod(getQueryCondition().getName(), Object.class);
    }

    @Override
    protected void invokeMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        getMethod().invoke(getQueryWrapper(), getColumn());
    }
}
