package com.itgirls.socialMedia.models;

public class Post {
    public String content;
    public User author;
    public int likes;
    public PostStatus status;

    public Post(String content, User author) {
        this.content = content;
        this.author = author;
        this.likes = 0;  // Начальное количество лайков
        this.status = PostStatus.DRAFT;
    }

    public void addLike() {
        switch (status) {
            case PUBLISHED:
                likes++;
                System.out.println("Like added");
                break;
            case DELETED:
                System.out.println("Unable to add like because post is deleted");
                break;
            case DRAFT:
                System.out.println("Unable to add like because post hasn't been posted yet");
                break;
            default:
                System.out.println("Unable to add like");
                break;
        }
    }

    public void publishPost() {
        status = PostStatus.PUBLISHED;
        System.out.println("Post published");
    }

    public void deletePost() {
        status = PostStatus.DELETED;
        System.out.println("Post deleted");
    }

    @Override
    public String toString() {
        return "Post{" +
                "content='" + content + '\'' +
                ", by author=" + author +
                ", count of likes=" + likes +
                ", status=" + status +
                '}';
    }
}