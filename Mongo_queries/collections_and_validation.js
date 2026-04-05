//use banking_nosql
db.createCollection("customer_activity_logs", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["accountId", "action", "status", "createdAt"],
      properties: {
        accountId: { bsonType: "long" },
        action: { bsonType: "string" },
        status: { bsonType: "string" },
        createdAt: { bsonType: "date" }
      }
    }
  }
});

db.createCollection("support_tickets", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["accountId", "subject", "status", "createdAt"],
      properties: {
        accountId: { bsonType: "long" },
        subject: { bsonType: "string" },
        status: { bsonType: "string" },
        createdAt: { bsonType: "date" }
      }
    }
  }
});