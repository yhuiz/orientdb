package com.orientechnologies.orient.core.storage.impl.local.paginated.wal.pageoperations.cluster.clusterpositionmap;

import com.orientechnologies.orient.core.storage.impl.local.paginated.wal.pageoperations.OPageOperation;
import com.orientechnologies.orient.core.storage.impl.local.paginated.wal.pageoperations.PageOperationTypes;

import java.nio.ByteBuffer;

public class OClusterPositionMapAllocatePageOperation implements OPageOperation {
  @Override
  public int toStream(byte[] content, int offset) {
    return 0;
  }

  @Override
  public void toStream(ByteBuffer buffer) {
  }

  @Override
  public int fromStream(byte[] content, int offset) {
    return 0;
  }

  @Override
  public int serializedSize() {
    return 0;
  }

  @Override
  public byte getId() {
    return PageOperationTypes.CLUSTER_POSITION_MAP_ALLOCATE;
  }
}
