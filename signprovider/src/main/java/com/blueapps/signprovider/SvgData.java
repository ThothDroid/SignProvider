package com.blueapps.signprovider;

public class SvgData {

    private final String pathData;
    private final String width;
    private final String height;

    public SvgData(String pathData, String width, String height) {
        this.pathData = pathData;
        this.width = width;
        this.height = height;
    }

    public String getPathData() {
        return pathData;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }
}
