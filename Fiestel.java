// Variables globales static 
int[] permutation; 
// Tableau pour stocker la permutation donnée static 
int decalage1; // Variable pour stocker l'ordre de décalage pour k1 static 
int decalage2; // Variable pour stocker l'ordre de décalage pour k2 
// Fonction pour générer les sous-clés de Feistel 
static void genererSousCles(int[] cle) { 
    // Appliquer la permutation H 
    int[] h = {6, 5, 2, 7, 4, 1, 3, 0}; 
    int[] p = new int[8]; 
    for (int i = 0; i < 8; i++) {
         p[i] = cle[h[i]]; 
         } 
         // Diviser la clé en deux blocs de 4 bits
         int[] k1 = new int[4]; 
         int[] k2 = new int[4]; 
         for (int i = 0; i < 4; i++) {
             k1[i] = p[i] ^ p[i+4];
              // k1 = k1' ⊕ k2' 
              k2[i] = p[i+4] & p[i]; 
              // k2 = k2' ∧ k1' 
            } 
              // Appliquer les décalages pour chaque sous-clé 
              k1 = decalerGauche(k1, decalage1); 
              k2 = decalerDroite(k2, decalage2); 
              // Afficher les sous-clés générées 
              System.out.println("Sous-clé k1 : "); 
              afficherTableau(k1); 
              System.out.println("Sous-clé k2 : "); 
              afficherTableau(k2); 
            } 
            // Fonction pour chiffrer un bloc de 8 bits avec Feistel 
            static int[] feistelChiffrement(int[] bloc) { 
                // Appliquer la permutation π 
                int[] pi = {4, 6, 0, 2, 7, 3, 1, 5}; 
                int[] p = new int[8]; 
                for (int i = 0; i < 8; i++) { 
                    p[i] = bloc[pi[i]]; 
                } // Diviser le bloc en deux blocs de 4 bits 
                int[] g0 = new int[4]; 
                int[] d0 = new int[4]; 
                for (int i = 0; i < 4; i++) { 
                    g0[i] = p[i]; 
                    d0[i] = p[i+4]; 
                } // Premier Round 
                int[] d1 = additionMod2(p(g0), k1); 
                int[] g1 = additionMod2(d0, ou(p(g0), k1)); 
                // Deuxième Round 
                int[] d2 = additionMod2(p(g1), k2); 
                int[] g2 = additionMod2(d1, ou(p(g1), k2)); 
                // Concaténer les deux blocs pour obtenir le texte chiffré 
                int[] c = concatener(g2, d2); 
                // Appliquer l'inverse de la permutation π-1 
                c = permutationInverse(c, pi); 
                // Retourner le texte chiffré 
                return c; } 
                // Fonction pour déchiffrer un bloc de 8 bits avec Feistel 
                static int[] feistelDechiffrement(int[] bloc) { 
                    // Appliquer la permutation π 
                    int[] pi = {4, 6, 0, 2, 7, 3, 1, 5}; 
                    int[] p = new int[8]; 
                    for (int i = 0; i < 8; i++) { 
                        p[i] = bloc[pi[i]]; 
                    } // Diviser le bloc en deux blocs de 4 bits 
                    int[] g2 = new int[4]; 
                    int[] d2 = new int[4]; 
                    for (int i = 0; i < 4; i++) { 
                        g2[i] = p[i]; 
                        d2[i] = p[i+4]; 
                    } // Premier Round 
                    int[] g1 = additionMod2(permutationInverse(d2, k2), p(g2)); 
                    int[] d1 = additionMod2(p(g1), ou(p(g2), k2)); 
                    // Deuxième Round 
                    int[] g0 = additionMod2(permutationInverse(d1, k1), p(g1)); 
                    int[] d0 = additionMod2(p