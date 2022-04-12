package example;


import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Response model for Kinesis Analytics Preprocessing Lambda function.
 */
public class KinesisCustomResponse implements Serializable {

    public enum Result {

        /**
         * Indicates that processing of this item succeeded.
         */
        Ok,

        /**
         * Indicate that the processing of this item failed
         */
        ProcessingFailed,

        /**
         * Indicates that this item should be silently dropped
         */
        Dropped
    }

    private static final long serialVersionUID = -4651154757825854471L;
    public List<Record> records;

    public KinesisCustomResponse() {
        super();
    }

    public KinesisCustomResponse(List<Record> records) {
        super();
        this.records = records;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public static class Record implements Serializable {
        private static final long serialVersionUID = -1583558686451236985L;
        public String recordId;
        public Result result;
        public Map<String, Object> metadata = new HashMap<String, Object>();

        public Record() {
            super();
        }

        public Record(String recordId, Result result, ByteBuffer data, Map<String,Object> metadata) {
            super();
            this.recordId = recordId;
            this.result = result;
            this.data = data;
            this.metadata = metadata;
        }

        public ByteBuffer data;

        public String getRecordId() {
            return recordId;
        }

        public void setRecordId(String recordId) {
            this.recordId = recordId;
        }

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }

        public ByteBuffer getData() {
            return data;
        }

        public void setData(ByteBuffer data) {
            this.data = data;
        }

        public Map<String, Object> getMetadata() {
            return metadata;
        }

        public void setMetadata(Map<String, Object> metadata) {
            this.metadata = metadata;
        }
    }
}
