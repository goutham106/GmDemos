package com.gm.gmannotation.util;

import com.gm.gmannotation.activities.model.TestModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gowtham on 4/20/2016.
 */
public class TempModel {

    public static List<TestModel> getTestModelData(){
        List<TestModel> testModels = new ArrayList<>();

        TestModel testModel = new TestModel();
        testModel.setName("Test1");
        testModel.setMobileNumber("9876543210");
        testModels.add(testModel);

        testModel = new TestModel();
        testModel.setName("Test2");
        testModel.setMobileNumber("0123456789");
        testModels.add(testModel);

        testModel = new TestModel();
        testModel.setName("Test3");
        testModel.setMobileNumber("8521479630");
        testModels.add(testModel);

        return testModels;
    }
}
