package com.example.applications.entity;

import java.io.Serializable;

public class AiMessageResponse implements Serializable {
    private String id;
    private String object;
    private int created;
    private int sentenceId;
    private boolean isEnd;
    private boolean isTruncated;
    private String result;
    private boolean needClearHistory;
    private String finishReason;
    private UsageDTO usage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(int sentenceId) {
        this.sentenceId = sentenceId;
    }

    public boolean isIsEnd() {
        return isEnd;
    }

    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public boolean isIsTruncated() {
        return isTruncated;
    }

    public void setIsTruncated(boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isNeedClearHistory() {
        return needClearHistory;
    }

    public void setNeedClearHistory(boolean needClearHistory) {
        this.needClearHistory = needClearHistory;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }

    public UsageDTO getUsage() {
        return usage;
    }

    public void setUsage(UsageDTO usage) {
        this.usage = usage;
    }

    public static class UsageDTO {
        private int promptTokens;
        private int completionTokens;
        private int totalTokens;

        public int getPromptTokens() {
            return promptTokens;
        }

        public void setPromptTokens(int promptTokens) {
            this.promptTokens = promptTokens;
        }

        public int getCompletionTokens() {
            return completionTokens;
        }

        public void setCompletionTokens(int completionTokens) {
            this.completionTokens = completionTokens;
        }

        public int getTotalTokens() {
            return totalTokens;
        }

        public void setTotalTokens(int totalTokens) {
            this.totalTokens = totalTokens;
        }
    }


}
