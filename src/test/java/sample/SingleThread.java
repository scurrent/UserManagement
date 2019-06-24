package sample;

//since I haven't worked much with multi-threading, this is just some code I wrote to try to se

public class SingleThread implements Runnable {

    String name;
    Thread thread;

    SingleThread(String threadname) {
    name = threadname;
        thread = new Thread(this, name);
        System.out.println("New thread: " + thread);
        thread.start();
    }

    public void run() {
        UserManagement instance = new UserManagement();
        try {
            for(int i = 7; i > 0; i--) {
                System.out.println("Add " + name + " - " + i );
                if(!instance.addUser("user"+name+i, "pass"+name)){
                    System.out.println("got a FALSE");
                }

                Thread.sleep(10);
            }

        }catch (InterruptedException e) {
            System.out.println(name + "Interrupted");
        }

        System.out.println(name + " exiting.");

    }

}

class MultiThread {

    public static void main(String args[]) {
        new SingleThread("A");
        new SingleThread("B");
        new SingleThread("C");

        try {

            Thread.sleep(8000);

        } catch (InterruptedException e) {

            System.out.println("Main thread Interrupted");

        }

        UserManagement instance = new UserManagement();
        //what does my list look like now
        System.out.println("SIZE: " + instance.listUsers().size());

        for(String user:  instance.listUsers()) {
            System.out.println(user);
        }

        System.out.println("Main thread exiting.");


    }

}