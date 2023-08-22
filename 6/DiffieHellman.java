class DiffieHellman {
    public static void main(String args[]) {
        int p = 51; /* publicly known (prime number) */
        int g = 6; /* publicly known (primitive root) */
        int x = 5; /* only Alice knows this secret */
        int y = 2; /* only Bob knows this secret */
        double aliceSends = (Math.pow(g, x)) % p;
        double bobComputes = (Math.pow(aliceSends, y)) % p;
        double bobSends = (Math.pow(g, y)) % p;
        double aliceComputes = (Math.pow(bobSends, x)) % p;
        double sharedSecret = (Math.pow(g, (x * y))) % p;
        System.out.println("Simulation of Diffie-Hellman key exchange algorithm\n");
        System.out.println("Alice Sends : " + aliceSends);
        System.out.println("Bob Computes : " + bobComputes);
        System.out.println("Bob Sends : " + bobSends);
        System.out.println("Alice Computes : " + aliceComputes);
        System.out.println("Shared Secret : " + sharedSecret);
        /* shared secrets should match and equality is transitive */
        if ((aliceComputes == sharedSecret) && (aliceComputes == bobComputes))
            System.out.println("Success: \nShared Secrets Matches! \nSecret: " + sharedSecret);
        else
            System.out.println("Error: Shared Secrets does not Match");
    }
}
