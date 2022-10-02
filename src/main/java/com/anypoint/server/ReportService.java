package com.anypoint.server;

import com.anypoint.*;
import com.anypoint.logic.SalesLogic;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@GRpcService
public class ReportService extends ReportServiceGrpc.ReportServiceImplBase {

    public SalesLogic salesLogic;

    @Autowired
    public ReportService(SalesLogic salesLogic) {
        this.salesLogic = salesLogic;
    }

    @Override
    public void getSales(SalesRequest request, StreamObserver<SalesResponse> responseObserver) {
        log.info("Report Service Request: " + request);

        SalesResponse response = salesLogic.getSales(request);

        log.info("Report Service Response: " + response);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
