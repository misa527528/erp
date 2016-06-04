package com.cqupt.mis.erp.model.prediction;


public class MarketPredictionDataset {
    //extends DefaultCategoryDataset

    private static final long serialVersionUID = 1L;
    private String marketName;
    private String predictionType;

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getPredictionType() {
        return predictionType;
    }

    public void setPredictionType(String predictionType) {
        this.predictionType = predictionType;
    }


}
