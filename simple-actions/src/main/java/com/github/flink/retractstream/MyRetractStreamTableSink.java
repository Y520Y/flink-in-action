package com.github.flink.retractstream;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.table.sinks.RetractStreamTableSink;
import org.apache.flink.table.sinks.TableSink;

/**
 * 自定义实现RetractStreamTableSink
 *
 * @Author: zlzhang0122
 * @Date: 2020/1/6 10:17 PM
 */
public class MyRetractStreamTableSink<Row> implements RetractStreamTableSink{

    private String[] fieldNames;

    private TypeInformation<Row>[] fieldTypes;

    @Override
    public TypeInformation getRecordType() {
        return new RowTypeInfo(fieldTypes, fieldNames);
    }

    @Override
    public void emitDataStream(DataStream dataStream) {
        dataStream.print();
    }

    @Override
    public TableSink configure(String[] strings, TypeInformation[] typeInformations) {
        return null;
    }

    @Override
    public TypeInformation<?>[] getFieldTypes() {
        return new TypeInformation<?>[0];
    }
}
