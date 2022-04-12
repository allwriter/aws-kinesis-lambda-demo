package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.KinesisFirehoseEvent;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static example.KinesisCustomResponse.Result.Ok;
import static example.KinesisCustomResponse.Result.ProcessingFailed;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * A sample KinesisFirehoseEvent handler
 *
 * 꼭 보세요 - https://docs.aws.amazon.com/ko_kr/firehose/latest/dev/data-transformation.html
 */
public class KinesisFirehoseEventHandler implements RequestHandler<KinesisFirehoseEvent, KinesisCustomResponse> {

    @Override
    public KinesisCustomResponse handleRequest(KinesisFirehoseEvent kinesisFirehoseEvent, Context context) {
        List<KinesisCustomResponse.Record> records = new ArrayList<>();
        LambdaLogger logger = context.getLogger();
        // S3 Dynamic Path 설정
        Map<String, Object> metadata = new HashMap<String, Object>();
        Map<String, String> partition_keys = new HashMap<String, String>();
        partition_keys.put("path1", "hello");
        partition_keys.put("path2", "kinesis");
        metadata.put("partitionKeys", partition_keys);

        for (KinesisFirehoseEvent.Record record : kinesisFirehoseEvent.getRecords()) {
            String recordData = new String(record.getData().array());
            String reversedString = "";
            // Your business logic
            try{
                reversedString = new StringBuilder(recordData).reverse().toString();
                // Ok(레코드가 성공적으로 변환되었음)
                records.add(new KinesisCustomResponse.Record(record.getRecordId(), Ok, ByteBuffer.wrap(reversedString.getBytes(UTF_8)), metadata));
                logger.log("Your Log"); // CloudWatch에 로그 남기기
            }catch (Exception e){
                //ProcessingFailed(레코드를 변환하지 못함)
                records.add(new KinesisCustomResponse.Record(record.getRecordId(), ProcessingFailed, record.getData(), metadata));
                logger.log("Your Log"); // CloudWatch에 로그 남기기
            }
        }
        return new KinesisCustomResponse(records);
    }
}
