package com.eventos.deportivos.backend.models.auth;

public class JwtConfig {
    public static final String LLAVE_SECRETA = "hola";

    public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
            + "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv/pFlVnB8Uta/OwCts+L\r\n"
            + "TmBE1EV7rM9tqbBt/PU0NvacELyiBfcUGlJTZRrF2ciL+tiBMWJDn+CATwJuu+6g\r\n"
            + "y2PgLx60I4RjRoYkzeKflIZCJPvh912oCcGccml7N4S4K0MBnjqC+vp5aCMsGxwY\r\n"
            + "IpDBtHrBWVA2kFPA+xCbdri8qgFi1sO23yIoETc378D4GmFLpONv7LBQf9m6LlMY\r\n"
            + "dXCT48lG1Ct14RrNMbD0FuUD6YMW/JVciemKeZOJV119jq3koPBFfNltn8xe2vXq\r\n"
            + "Gj48wpu74guf++IhtTtsXros02uN5736fu7ZVTl+LW2zax3aEqgSulRNY2+lQFNT\r\n"
            + "CwIDAQAB\r\n"
            + "-----END PUBLIC KEY-----";

    public static final String RSA_PRIVATE="-----BEGIN RSA PRIVATE KEY-----\r\n"
            + "MIIEpQIBAAKCAQEAv/pFlVnB8Uta/OwCts+LTmBE1EV7rM9tqbBt/PU0NvacELyi\r\n"
            + "BfcUGlJTZRrF2ciL+tiBMWJDn+CATwJuu+6gy2PgLx60I4RjRoYkzeKflIZCJPvh\r\n"
            + "912oCcGccml7N4S4K0MBnjqC+vp5aCMsGxwYIpDBtHrBWVA2kFPA+xCbdri8qgFi\r\n"
            + "1sO23yIoETc378D4GmFLpONv7LBQf9m6LlMYdXCT48lG1Ct14RrNMbD0FuUD6YMW\r\n"
            + "/JVciemKeZOJV119jq3koPBFfNltn8xe2vXqGj48wpu74guf++IhtTtsXros02uN\r\n"
            + "5736fu7ZVTl+LW2zax3aEqgSulRNY2+lQFNTCwIDAQABAoIBAApKZxp3Yj8F9A2k\r\n"
            + "tQyJmYUNAgOC6fs8bztuWqb5md/sXjdDdUfuk6RJj3DW+44OAvriSiKUit1yEk6p\r\n"
            + "6benNfVRGD5OXgedXBj2rdhfcoBqpfcHjXhOC+jh+PnlD5bNRXYENHz/0++SJ5ne\r\n"
            + "cMnPOrf3nHqJLf4Un6gi5TBQFpVJf/PZy9MAC+0PYk54hSUWs67BtGzeBIQPFFZZ\r\n"
            + "WLV4BkkmF5SkEoVggchFC+XdivO1diWclcms8Bvken/wDR/FU75ooCZz1vAM8WQE\r\n"
            + "HX2QzxsdrYOuaXCxNqy7FP+W5yfkld2j6DqxPXtS+yB9z8+e4+QmpAuYI/uNkokW\r\n"
            + "QZS1uLkCgYEA8A4xbLtHEayxWGA91xu8+9GBZ+UJSUVZpQi4A4iMRZX0ipVMxpX8\r\n"
            + "WY84p+evRPnj/yPq87GRlBXItvoV8UcZl/YnDUehcXyCf2/Z6R6rFWrAloL6DeJ8\r\n"
            + "1QFJJqpm2EkoFk6U4YPaAhXZyMsr1q+xnzLYXjDQF+/OTOYefX+bUO8CgYEAzLqV\r\n"
            + "LVv8+oXIqx67dwKE6b3hCtcQ7EcH8F8Its6IGUFAX635ZF98DVMP0wXWxo9PcItj\r\n"
            + "kWJH7yk+5M+uxzmNBeW+jDZB1BULQcZRhW0hm+xtf6xKFBWpzT3y/12e0T0ou3wu\r\n"
            + "7l5kESVZrWYPFvQ/I4Uc4Trz66fxC3H20WbYZ6UCgYEAy/By4tnz1bst61PQFbh2\r\n"
            + "BEWHt7kn+3yUXRWcJzxRVyi/WSDf2fJAMeHwEw1MpL/bh71+9MuNTw52t6SdEZ8C\r\n"
            + "ykwP/0M0j9Jkl1lx+o/YbtiDhxr8zuD0ho2SFOw9dVApY1IdP72hb5YasHCS7M/U\r\n"
            + "idhLYzH7B+ruC/yQOLQ5QjcCgYEAnMlvoXNHRFzclyBMFRJa099Uz0ZxcWHclJtT\r\n"
            + "QxzcYer079F+ov31NQcdoVBhcWMjRV+aeya3TFNcFUNv6KcMdy3BRgPvWgpbup20\r\n"
            + "pOg0ywK/wP0jJkqI5ePBiwwZqsiZvyuVjXvAStk92mvEtqGLERrKSTsqvpi5Izi9\r\n"
            + "+xQ2OvkCgYEA4/jHWyACPdUzPMu7gbhlrOAlHngfGHDMx/GZZ6usBjpD9+Dc0oVv\r\n"
            + "nTWME+8UtUTLu3bX2TEQCWUBUO5GV1G/9ZcDOzbYQytHpaYYST9F6+GJqulKcbTd\r\n"
            + "ZEJI6KGZucew7AnaIrDZ1BlLmWa5LUZPfbYVS9PWbzMlnOs5uUyuu4A=\r\n"
            + "-----END RSA PRIVATE KEY-----";
}
