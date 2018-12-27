package com.study.test;

/**
 * Created by BGL on 2017/7/5.
 */
public class GitTest {

    private GitTest() {

    }

    public static GitTest getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    public enum Singleton{
        INSTANCE;

        private GitTest instance;

        Singleton(){
            this.instance = new GitTest();
        }

        public GitTest getInstance(){
            return instance;
        }
    }
}
