package com.example.wsbp.service;

public interface ISampleService {

    /**
     * @return 現在の時:分:秒を文字列で返す
     */
    public String makeCurrentHMS();

    /**
     * @return 0～9の乱数を整数型で返す
     */
    public int makeRandInt();
}
