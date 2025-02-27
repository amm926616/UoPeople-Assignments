package org.example.logger;

public class PerformanceLogger {

    public PerformanceLoggerInfo start(String methodName) {
        return new PerformanceLoggerInfo(methodName,
                System.currentTimeMillis());
    }

    public void end(PerformanceLoggerInfo info) {
        long start = info.getStartTime();
        long end = System.currentTimeMillis();
        String msg = info.getMethodName() + " took " + (end - start) + "ms";
        System.out.println(msg);
    }

    public static class PerformanceLoggerInfo {
        private String methodName;
        private long startTime;

        public PerformanceLoggerInfo(String methodName, long startTime) {
            this.methodName = methodName;
            this.startTime = startTime;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }
    }


    public static void main(String[] args) {

    }
}
