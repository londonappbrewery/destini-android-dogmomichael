package com.londonappbrewery.destini;

public class StoryLine2 {
    private int mStoryId;
    private int mAnswer1Id;
    private int mAnswer2Id;

    public StoryLine2(int storyId, int answer1Id, int answer2Id){
        this.mStoryId = storyId;
        this.mAnswer1Id = answer1Id;
        this.mAnswer2Id = answer2Id;
    }
    public StoryLine2(int storyId){
        this.mStoryId = storyId;
    }

    public int getStoryId() {
        return mStoryId;
    }

    public int getAnswer1Id() {
        return mAnswer1Id;
    }

    public int getAnswer2Id() {
        return mAnswer2Id;
    }
}
