-> Main classes : CashMachine, CheckingAccount
-> Interfaces: RemoteService

    - CashMachine: log(): String; withdraw(): String; deposit(): String; balance(): String
    - CheckingAccount: accountId: Integer; password: String; balance: Float
        -> implements: RemoteService: recoveryAccountInfo(); persistAccountChange();