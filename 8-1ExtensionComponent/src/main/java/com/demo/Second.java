package com.demo;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Second implements JavaSamplerClient {
    //图形化界面中显示的变量名称
    private  static final String URLNAME = "URL";
    //界面中显示的变量的值
    private static final String DEFAULTURL = "https://www.baidu.com/";
    //界面中用户输入的url
    private String inputUrl;
    //用来存储响应的数据，目的是将相应结果放到查看结果树中
    private String resultData;

    /**
     * 该方法决定了jmeter中显示的哪些属性
     * @return arguments
     */
    public Arguments getDefaultParameters() {
        Arguments arguments = new Arguments();
        arguments.addArgument(URLNAME, DEFAULTURL);
        return arguments;
    }

    /**
     * 接收用户输入的变量
     * @param javaSamplerContext
     */
    public void setupTest(JavaSamplerContext javaSamplerContext) {
        inputUrl = javaSamplerContext.getParameter(URLNAME, DEFAULTURL);//获得用户输入的url
        System.out.println("setupTest");
        System.out.println("用户输入的url地址是：" + inputUrl);
    }

    /**
     * 发起http请求，设置查看结果树的信息
     * @param javaSamplerContext
     * @return result
     */
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult result = new SampleResult();
        System.out.println("runTest");
        StringBuffer sb = new StringBuffer();//定义一个可变长度的string，字符串大小超过容量时，会自动增加容量，可以append
        try {
            //get请求
            URL url = new URL(inputUrl);
            URLConnection conn = url.openConnection();
            byte[] buffer = new byte[1024];  //定义存储的缓冲区
            int len;

            result.sampleStart();//标记事务的开始，用于后续统计
            InputStream in = conn.getInputStream(); //定义输入流
            while ((len=in.read(buffer))!=-1) {  //代表读完了
                resultData = new String(buffer,"UTF-8");
                sb.append(resultData); //将缓冲区的结果写入StringBuffer中
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultData = sb.toString();//将StringBuffer的数据转换为string

        //result.setSampleLabel("自定义java请求访问");
        result.setSuccessful(true); //告诉查看结果树访问是否成功
        result.setResponseData(resultData, null);  //设置响应信息
        result.setDataType(SampleResult.TEXT); //设置响应结果数据类型
        return result;
    }

    public void teardownTest(JavaSamplerContext javaSamplerContext) {
        System.out.println("teardownTest");
    }
}
