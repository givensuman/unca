public class StringQueue {
    private String data = "";

    public void enqueue(String item) {
        if (this.data.equals("")) {
            this.data = item;
        } else {
            this.data += ":" + item;
        }
    }

    public String dequeue() {
        if (this.data.equals(""))
            return null;

        int delimiterIndex = this.data.indexOf(":");
        String result = this.data.substring(0, delimiterIndex);

        if (this.count() > 1) {
            this.data = this.data.substring(delimiterIndex + 1, this.data.length());
        } else {
            this.data = "";
        }

        return result;
    }

    public int count() {
        return this.data.split(":").length;
    }

    public String toString() {
        return this.data;
    }
}
