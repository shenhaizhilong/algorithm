package com.hui.algorithm;

import java.util.*;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/23 9:30
 *
 *355. Design Twitter
 * DescriptionHintsSubmissionsDiscussSolution
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:
 *
 * postTweet(userId, tweetId): Compose a new tweet.
 * getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
 * follow(followerId, followeeId): Follower follows a followee.
 * unfollow(followerId, followeeId): Follower unfollows a followee.
 * Example:
 *
 * Twitter twitter = new Twitter();
 *
 * // User 1 posts a new tweet (id = 5).
 * twitter.postTweet(1, 5);
 *
 * // User 1's news feed should return a list with 1 tweet id -> [5].
 * twitter.getNewsFeed(1);
 *
 * // User 1 follows user 2.
 * twitter.follow(1, 2);
 *
 * // User 2 posts a new tweet (id = 6).
 * twitter.postTweet(2, 6);
 *
 * // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
 * // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 * twitter.getNewsFeed(1);
 *
 * // User 1 unfollows user 2.
 * twitter.unfollow(1, 2);
 *
 * // User 1's news feed should return a list with 1 tweet id -> [5],
 * // since user 1 is no longer following user 2.
 * twitter.getNewsFeed(1);
 *
 */
public class Twitter {
    private  static int timeStamp = 0;
    private  Map<Integer, User> userMap = new HashMap<>();
    /** Initialize your data structure here. */
    public Twitter() {


    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {

        User user = null;
        if(!userMap.containsKey(userId))
        {
            user = new User(userId);
            userMap.put(userId, user);
        }else {
            user = userMap.get(userId);
        }
        user.post(tweetId);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>(10);
        if(!userMap.containsKey(userId))   return res;
        int size = 0;
        PriorityQueue<Tweet>  maxHeap = new PriorityQueue<>((Tweet t1, Tweet t2) -> t2.time - t1.time);

        for(int user: userMap.get(userId).followed)
        {
            Tweet t = userMap.get(user).Tweet_Head;
            if(t != null)
            {
                maxHeap.offer(t);
            }
        }


        while (!maxHeap.isEmpty() && size < 10)
        {
            Tweet t = maxHeap.poll();
            res.add(t.id);
            size++;
            if(t.next != null)
            {
                maxHeap.offer(t.next);
            }

        }

        return res;

    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {


        if(!userMap.containsKey(followerId))
        {
            User user = new User(followerId);
            userMap.put(followerId, user);  // put it to the userMap, atomic operation

        }
        if(!userMap.containsKey(followeeId))
        {
            User user = new User(followeeId);
            userMap.put(followeeId, user);
        }
        userMap.get(followerId).follow(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        // if follower/followe doesn't exist, just return , the operation is invalid;
        if(!userMap.containsKey(followerId) || !userMap.containsKey(followeeId) || followeeId == followerId)return;
        userMap.get(followerId).unFollow(followeeId);
    }


    private static class Tweet{
        int id;
        int time;
        Tweet next;

        public Tweet(int id)
        {
            this.id  = id;
            time = timeStamp++;  // atomic operation
        }
    }

    private static class User{
        int id;
        HashSet<Integer> followed;
        Tweet Tweet_Head;

        public User(int id)
        {
            this.id = id;
            followed = new HashSet<>();
            followed.add(this.id);  // first follow itself
        }

        public void follow(int id)
        {
            followed.add(id);
        }

        public void unFollow(int id)
        {
            followed.remove(id);
        }



        // add it to the head of tweet list
        public void post(int id)
        {
            Tweet tmp = new Tweet(id);
            tmp.next = Tweet_Head;
            Tweet_Head = tmp;
        }

    }

    public static void main(String[] args) {


        Twitter twitter = new Twitter();
        twitter.postTweet(1,1);
        System.out.println(twitter.getNewsFeed(1));
        twitter.follow(2,1);
        System.out.println(twitter.getNewsFeed(2));
        twitter.unfollow(2,1);
        System.out.println(twitter.getNewsFeed(2));
    }

}
