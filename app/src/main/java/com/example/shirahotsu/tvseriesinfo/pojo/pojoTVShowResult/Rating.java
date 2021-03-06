
package com.example.shirahotsu.tvseriesinfo.pojo.pojoTVShowResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Rating implements Serializable {

    @SerializedName("average")
    @Expose
    private Object average;

    public Object getAverage() {
        return average;
    }

    public void setAverage(Object average) {
        this.average = average;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("average", average).toString();
    }

}
