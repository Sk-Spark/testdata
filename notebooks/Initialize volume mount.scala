// Databricks notebook source
val configs = Map(
  "fs.azure.account.auth.type" -> "OAuth",
  "fs.azure.account.oauth.provider.type" -> "org.apache.hadoop.fs.azurebfs.oauth2.ClientCredsTokenProvider",
  "fs.azure.account.oauth2.client.id" -> "5496be3e-8ec4-45e5-ac8b-0130c4293355",
  "fs.azure.account.oauth2.client.secret" -> dbutils.secrets.get(scope = "training-scope", key = "appsecret"),
  "fs.azure.account.oauth2.client.endpoint" -> "https://login.microsoftonline.com/7d556c7e-1de7-49cc-b014-ad1058fa4c8a/oauth2/token")

// COMMAND ----------

dbutils.fs.mount(
  source = "abfss://casestudydata@shuku2storage.dfs.core.windows.net/",
  mountPoint = "/mnt/data",
  extraConfigs = configs)

// COMMAND ----------

val mounts = dbutils.fs.mounts()

for(mount <- mounts) {
  println(mount.source + ", " + mount.mountPoint)
}

// COMMAND ----------

// MAGIC 
// MAGIC %fs
// MAGIC 
// MAGIC ls /mnt/data

// COMMAND ----------

import com.microsoft.crmsystem.udfs._