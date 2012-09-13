package cheese;

class CustomerRunnable implements Runnable {

    CheeseStore cheeseStore;

    public void setName(String name) {
        this.name = name;
    }

    String name;

    public void setCheeseStore(CheeseStore cheeseStore) {
        this.cheeseStore = cheeseStore;
    }

    @Override
    public void run() {
        while (true) {

            cheeseStore.getCheese(name);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}

