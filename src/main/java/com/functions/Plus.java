package com.functions;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Plus extends AbstractFunction {
    private Object[] values;
    private CompoundVariable first, second;  //存储第一个和第二个参数

    /**
     * 执行方法
     * @param sampleResult
     * @param sampler
     * @return
     * @throws InvalidVariableException
     */
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {
        System.out.println("execute");
        first = (CompoundVariable) values[0];
        System.out.println("first:"+first);
        second = (CompoundVariable) values[1];
        System.out.println("second:"+second);
        int count = new Integer(first.execute().trim()) + new Integer(second.execute().trim());
        System.out.println("sum：" + count);
        return String.valueOf(count);
    }

    /**
     * 设置参数，接收用户传递的参数
     * @param collection
     * @throws InvalidVariableException
     */
    public void setParameters(Collection<CompoundVariable> collection) throws InvalidVariableException {
        checkParameterCount(collection, 2);//检查参数是否为2个
        values = collection.toArray();  //转换成数组
        System.out.println("setParameters");
    }

    /**
     * 函数名称
     * @return
     */
    public String getReferenceKey() {
        System.out.println("getReferenceKey");
        return "__MyDemoPlus";
    }

    /**
     * 参数描述
     * @return
     */
    public List<String> getArgumentDesc() {
        System.out.println("getArgumentDesc");
        List desc = new ArrayList();
        desc.add("the first number");
        desc.add("the second number");
        return desc;
    }
}
