package com.spsampes.cyclicbarrier;

import com.spsampes.cyclicbarrier.thread.StorageSupervisor;
import com.spsampes.cyclicbarrier.thread.Transporter;
import com.spsampes.cyclicbarrier.util.Producer;
import com.spsampes.cyclicbarrier.util.Storage;
import com.spsamples.common.AbstractSampleExecutor;
import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;

/**
 * Sample of simple {@link java.util.concurrent.CyclicBarrier} usage.
 *
 * There is three threads: - first - {@link StorageSupervisor} - reach barrier straightaway and just
 * waiting for {@link Storage} to fill up. Other two threads - {@link Transporter} - represents some
 * transfer logic, it takes {@link Producer} instance and {@link Storage} instance, further just
 * takes data from producer and puts into storage. When producer data exhausted, barrier considered
 * reached.
 *
 * When all transporters reached barrier, storage supervisor prints storage content.
 */
public class CyclicBarrierExecutor extends AbstractSampleExecutor {

  protected CyclicBarrierExecutor(String sampleName) {
    super(sampleName);
  }

  @Override
  protected void custom() {
    CyclicBarrier cb = new CyclicBarrier(3);
    Storage<String> storage = new Storage<>(10);

    new StorageSupervisor(cb, storage).start();
    new Transporter<>(
        "t1",
        cb,
        new Producer<>(Arrays.asList("A_t1", "B_t1", "C_t1")),
        storage
    ).start();
    new Transporter<>(
        "t2",
        cb,
        new Producer<>(Arrays.asList("D_t2", "E_t2", "F_t2", "G_t2", "H_t2")),
        storage
    ).start();
  }
}
