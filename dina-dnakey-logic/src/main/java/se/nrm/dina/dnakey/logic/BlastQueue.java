package se.nrm.dina.dnakey.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import se.nrm.dina.dnakey.logic.config.ConfigProperties;
import se.nrm.dina.dnakey.logic.metadata.BlastMetadata;

/**
 * BlastQueue uses a ManagedExecutorService to submit task in queue
 *
 * @author idali
 */
@Slf4j
public class BlastQueue implements Serializable {

  @Inject
  private ConfigProperties config;

  private String blastPath;
  private String blastDbPath;

  @Resource(lookup = "java:jboss/ee/concurrency/executor/default")                  // wildfly
  ManagedExecutorService highPriority;

  public BlastQueue() {
  }

  public BlastQueue(ManagedExecutorService highPriority) {
    this.highPriority = highPriority;
  }

  @PostConstruct
  public void init() {
    blastPath = config.getBlastnPath();
    blastDbPath = config.getDbPath();
  }

  /**
   * run task in queue
   *
   * @param filePathList
   * @param dbname
   * @return List of results
   */
  public List<BlastMetadata> run(List<String> filePathList, String dbname) { 
    log.info("run : {}", dbname);

    List<BlastMetadata> resultList = new ArrayList<>(); 
    List<Future> list = new ArrayList<>();
    filePathList
            .parallelStream()
            .forEachOrdered(x -> {
              list.add(highPriority.submit(new BlastCallableTask(x, dbname, blastPath, blastDbPath)));
            });

    boolean finished = false;
    while (!finished) { 
      finished = list.stream().noneMatch((f) -> (!f.isDone()));
    }

    list.stream().forEach((future) -> {
      try {
        resultList.add((BlastMetadata) future.get());
      } catch (InterruptedException | ExecutionException ex) {
        log.error(ex.getMessage());
      }
    });
    return resultList;
  } 
}
