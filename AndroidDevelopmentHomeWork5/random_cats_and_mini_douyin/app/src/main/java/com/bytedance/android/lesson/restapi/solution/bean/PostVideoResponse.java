package com.bytedance.android.lesson.restapi.solution.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Xavier.S
 * @date 2019.01.18 17:53
 */
public class PostVideoResponse {

    // TODO-C2 (3) Implement your PostVideoResponse Bean here according to the response json
    @SerializedName("success") private boolean success;
    @SerializedName("feeds") private Item item;

    //public Boolean getSuccess(){
     //   return success;
    //}


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Item getItem(){
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public class Item {
        @SerializedName("student_id")
        private String studentId;
        @SerializedName("user_name")
        private String username;
        @SerializedName("image_url")
        private String imageUrl;
        @SerializedName("video_url")
        private String videoUrl;

        public String getStudentId() {
            return studentId;
        }

        public String getUsername() {
            return username;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getVideoUrl() {
            return videoUrl;
        }
    }
}
