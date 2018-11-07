package io.cmpe272.crackit;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.twitter.search.TwitterSearchComponent;
import org.apache.camel.component.twitter.timeline.TwitterTimelineComponent;
import org.apache.camel.component.twitter.streaming.TwitterStreamingComponent;
import org.apache.camel.component.twitter.directmessage.TwitterDirectMessageComponent;
import org.apache.camel.component.websocket.WebsocketComponent;

public class TwitterWebSocketRoute extends RouteBuilder {
	
	private static String consumerKey = "TRFyAlXfTxkU3GOVx12k5h1zk";
	private static String consumerSecret = "IMbqlYRymB8FL0fmzhhjSiWdTuzjscOhxzVU9uMclIbDoU8eRX";
	private static String accessToken = "1153011601-V5cGxRgomQAClhC6DafNmH9Wko74j3dvvgsZabR";
	private static String accessTokenSecret = "UhSelXgDH9mianKuX8R6i4G82x1xKoYTzsE64TnR7C0VH";
	
//	public static String consumerKey = "CZPa7rNbtr3Wh2SanXkJCzbFM";
//	    public static String consumerSecret = "y3xXdXXwMLT98W5AcQ30wrCAd4mtuzMGofdNoVLWmOChDQHvAn";
//	    public static String accessToken = "109462293-FVE3ZsczSMgVrSxhQQSWKXRHFqYaXGQXkpquZ2ZO";
//	    public static String accessTokenSecret = "OTfDryxAJWolLECcpkhYnia7HqOUAuBzPjLGvmjiF28nh";
	
//	private static String consumerKey = "NMqaca1bzXsOcZhP2XlwA";
//    private static String consumerSecret = "VxNQiRLwwKVD0K9mmfxlTTbVdgRpriORypnUbHhxeQw";
//    private static String accessToken = "26693234-W0YjxL9cMJrC0VZZ4xdgFMymxIQ10LeL1K8YlbBY";
//    private static String accessTokenSecret = "BZD51BgzbOdFstWZYsqB5p5dbuuDV12vrOdatzhY4E";

    private int port = 9090;
    private String searchTerm;
    private int delay = 10000;

    @Override
    public void configure() throws Exception {
        // setup Camel web-socket component on the port we have defined
        WebsocketComponent wc = getContext().getComponent("websocket", WebsocketComponent.class);
        wc.setPort(port);
        // we can serve static resources from the classpath: or file: system
        wc.setStaticResources("classpath:.");

        // setup Twitter component
        TwitterSearchComponent twitterSearchComponent = getContext().getComponent("twitter-search", TwitterSearchComponent.class);
        twitterSearchComponent.setAccessToken(accessToken);
        twitterSearchComponent.setAccessTokenSecret(accessTokenSecret);
        twitterSearchComponent.setConsumerKey(consumerKey);
        twitterSearchComponent.setConsumerSecret(consumerSecret);
        
        TwitterTimelineComponent twitterTimelineComponent = getContext().getComponent("twitter-timeline", TwitterTimelineComponent.class);
        twitterTimelineComponent.setAccessToken(accessToken);
        twitterTimelineComponent.setAccessTokenSecret(accessTokenSecret);
        twitterTimelineComponent.setConsumerKey(consumerKey);
        twitterTimelineComponent.setConsumerSecret(consumerSecret);
        
        TwitterStreamingComponent twitterStreamingComponent = getContext().getComponent("twitter-streaming", TwitterStreamingComponent.class);
        twitterStreamingComponent.setAccessToken(accessToken);
        twitterStreamingComponent.setAccessTokenSecret(accessTokenSecret);
        twitterStreamingComponent.setConsumerKey(consumerKey);
        twitterStreamingComponent.setConsumerSecret(consumerSecret);
        
        TwitterDirectMessageComponent twitterDirectMessageComponent = getContext().getComponent("twitter-directmessage", TwitterDirectMessageComponent.class);
        twitterDirectMessageComponent.setAccessToken(accessToken);
        twitterDirectMessageComponent.setAccessTokenSecret(accessTokenSecret);
        twitterDirectMessageComponent.setConsumerKey(consumerKey);
        twitterDirectMessageComponent.setConsumerSecret(consumerSecret);
               
        //working
      fromF("twitter-search://keywords=iphone&delay=4000&count=1")
  		.to("log:tweet")
  		.to("websocket:camel-tweet?sendToAll=true");
      
//      //working
      from("twitter-timeline://home?type=polling&delay=6000&count=2")
      	.to("log:timeline-home")
      	.to("websocket:camel-timeline-home?sendToAll=true");
      
//      //working
      from("twitter-timeline://mentions?user=JuntengTan&count=2")
      	.to("log:timeline-mentions")
      	.to("websocket:camel-streaming-sample?sendToAll=true");
      
//      //working
      from("twitter-streaming://sample?type=polling&delay=10000&count=2")
  		.to("log:streaming-sample")
  		.to("websocket:camel-streaming-sample?sendToAll=true");
      
//      //working
      from("twitter-streaming://filter?keywords=cocacola&type=polling&delay=10000&count=3")
  		.to("log:streaming-filter")
  		.to("websocket:camel-streaming-filter?sendToAll=true");
      
        
    }
}
