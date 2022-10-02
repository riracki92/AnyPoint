package com.anypoint.server;

import com.anypoint.*;
import com.anypoint.logic.PaymentLogic;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@GRpcService
public class PriceService extends PriceServiceGrpc.PriceServiceImplBase {

    public PaymentLogic paymentLogic;

    @Autowired
    public PriceService(PaymentLogic paymentLogic) {
        this.paymentLogic = paymentLogic;
    }

    @Override
    public void getPrice(PriceRequest request, StreamObserver<PriceResponse> responseObserver) {
        log.info("Request: " + request);

        PriceResponse response = paymentLogic.doPayment(request);

        log.info("Response: " + response);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
