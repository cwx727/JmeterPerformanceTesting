package com.demo;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class First extends AbstractJavaSamplerClient {

    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        System.out.println("hello jmeter!!!");
        return null;
    }
}
