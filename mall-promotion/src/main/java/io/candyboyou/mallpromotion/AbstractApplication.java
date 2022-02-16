package io.candyboyou.mallpromotion;

import io.candyboyou.mallpromotion.config.Env;

import java.util.TimeZone;

public abstract class AbstractApplication {

    public AbstractApplication() {
    }

    public static void start(String[] args) {
        String env = Env.dev.name();
        if (args != null && args.length > 0) {
            String[] var2 = args;
            int var3 = args.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String arg = var2[var4];
                if (null != arg && arg.startsWith("--spring.profiles.active=")) {
                    env = arg.substring("--spring.profiles.active=".length());
                }
            }
        }

        System.out.println(" *********************************");
        System.out.println("*** \t\t\t\t***");
        System.out.println("*** server is starting,evn is\t***");
        System.out.println("*** \t\t\t\t***");
        System.out.println("***\t\t" + env.toUpperCase() + " !\t\t***");
        System.out.println("*** \t\t\t\t***");
        System.out.println(" *********************************");
        System.setProperty("user.env", env.toLowerCase());
        System.setProperty("sun.net.client.defaultConnectTimeout", "2000");
        System.setProperty("sun.net.client.defaultReadTimeout", "1500");
        System.setProperty("user.timezone", "GMT+8");
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    }

}
