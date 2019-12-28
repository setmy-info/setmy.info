package info.setmy;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args)
        {
                final ZeebeClient client = ZeebeClient.newClientBuilder()
                            // change the contact point if needed
                                        .brokerContactPoint("127.0.0.1:26500")
                                                    .usePlaintext()
                                                                .build();
                                                                
                                                                        System.out.println("Connected.");
                                                                        
                                                                                // ...
                                                                                
                                                                                        client.close();
                                                                                                System.out.println("Closed.");
                                                                                                    }
}
