package com.orientechnologies.orient.core.metadata.sequence;

import com.orientechnologies.orient.core.db.ODatabaseDocumentInternal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.hook.ODocumentHookAbstract;
import com.orientechnologies.orient.core.record.impl.ODocument;

/**
 * Update the in-memory function library.
 *
 * @author Matan Shukry (matanshukry@gmail.com)
 * @since 3/2/2015
 */
public class OSequenceTrigger extends ODocumentHookAbstract {
  public OSequenceTrigger(ODatabaseDocument database) {
    super(database);
    setIncludeClasses(OSequence.CLASS_NAME);
  }

  @Override
  public SCOPE[] getScopes() {
    return new SCOPE[] { SCOPE.CREATE, SCOPE.UPDATE, SCOPE.DELETE };
  }

  @Override
  public DISTRIBUTED_EXECUTION_MODE getDistributedExecutionMode() {
    return DISTRIBUTED_EXECUTION_MODE.TARGET_NODE;
  }

  @Override
  public void onRecordAfterCreate(final ODocument iDocument) {
    if (getDatabase().getStorage().isDistributed()) {
      getSequenceLibrary().onSequenceCreated(getDatabase(), iDocument);
    }
  }

  private ODatabaseDocumentInternal getDatabase() {
    return (ODatabaseDocumentInternal) database;
  }

  @Override
  public void onRecordAfterUpdate(final ODocument iDocument) {
    if (getDatabase().getStorage().isDistributed()) {
      getSequenceLibrary().onSequenceUpdated(getDatabase(), iDocument);
    }
  }

  @Override
  public void onRecordAfterDelete(final ODocument iDocument) {
    if (getDatabase().getStorage().isDistributed()) {
      getSequenceLibrary().onSequenceDropped(getDatabase(), iDocument);
    }
  }

  private OSequenceLibraryImpl getSequenceLibrary() {
    return ((OSequenceLibraryProxy) getDatabase().getMetadata().getSequenceLibrary()).getDelegate();
  }
}