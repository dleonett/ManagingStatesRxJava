package com.example.danielleonett.myapplication.ui.main;

/**
 * Created by daniel.leonett on 16/01/2018.
 */

public class UiStateModel {

    private boolean inProgress;
    private boolean success;
    private String errorMessage;
    private DataModel data;

    public UiStateModel(boolean inProgress, boolean success, String errorMessage, DataModel data) {
        this.inProgress = inProgress;
        this.success = success;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public UiStateModel() {}

    public boolean isInProgress() {
        return inProgress;
    }

    public boolean isSuccess() {
        return success;
    }

    public DataModel getData() {
        return data;
    }

    public boolean isError() {
        return errorMessage != null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static UiStateModel idle() {
        return new UiStateModel();
    }

    public static UiStateModel inProgress() {
        return new UiStateModel(true, false, null, null);
    }

    public static UiStateModel success(DataModel data) {
        return new UiStateModel(false, true, null, data);
    }

    public static UiStateModel failure(String errorMessage) {
        return new UiStateModel(false, false, errorMessage, null);
    }
}
