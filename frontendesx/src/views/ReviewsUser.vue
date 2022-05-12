
<template>
  <nav>
    <v-toolbar flat app class="blue">
      <v-toolbar-side-icon @click="drawer = !drawer" class="grey--text">
        <v-app-bar-nav-icon></v-app-bar-nav-icon>
      </v-toolbar-side-icon>
      <v-toolbar-title class="text black--text">
        <span class="font-weight-light">User</span>
        <span class="font-bolt"> ESX</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn flat color="grey" @click="$router.push({ name: 'login' })">
        <span>Sign Out</span>
        <v-icon right>mdi-export</v-icon>
      </v-btn>
    </v-toolbar>

    <v-navigation-drawer app v-model="drawer" class="grey">
      <v-list-item-group
        v-model="group"
        active-class="deep-purple--text text--accent-5"
      >
        <v-list-item
          v-for="link in links"
          :key="link.text"
          router
          :to="link.route"
        >
          <v-list-tile-action>
            <v-icon class="black--text">{{ link.icon }}</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title class="white--text">{{
              link.text
            }}</v-list-tile-title>
          </v-list-tile-content>
        </v-list-item>
      </v-list-item-group>
    </v-navigation-drawer>
    <v-row>
      <v-col lg="8">
        <v-form ref="form" class="ml-6 mr-6">
          <div id="app">
            <v-app>
              <v-list subheader two-line>
                <v-subheader inset>Reviews</v-subheader>

                <v-list-item v-for="(review, idx)  in reviews" :item="review" :key="idx">
                  <v-list-item-avatar>
                    <v-icon class="grey lighten-1" dark> mdi-account </v-icon>
                  </v-list-item-avatar>
                  <!-- @click="userReview(review.idUser)" -->
                  <v-list-item-content>
                    <v-list-item-subtitle v-text="review.user.firstName+ ' '+review.user.lastName "></v-list-item-subtitle>

                    <v-list-item-title
                      v-text="review.comment"
                    ></v-list-item-title>

                    <!-- userReview(review.idUser) -->
                    <v-list-item-subtitle>
                      <v-rating
                        v-model="review.note"
                        readonly
                        half-increments
                      ></v-rating
                    ></v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </v-app>
          </div>
        </v-form>
      </v-col>
      <v-col>
        <v-btn
          color="success"
          class="mr-4"
          @click="$router.push({ name: 'gyms' })"
        >
          Back
        </v-btn>
      </v-col>
    </v-row>
  </nav>
</template>

<script>
import axios from "axios";

export default {
  name: "ReviewsUser",
  data() {
    return {
      comment: "",
      rating: 0,
      reviews: [],
      drawer: false,
      links: [
        { icon: "mdi-home", text: "Home", route: "/home" },
        // { icon: "mdi-account-multiple", text: "Users", route: "/home" },
        {
          icon: "mdi-clipboard-text",
          text: "Subscriptions",
          route: "/home/subscriptions",
        },
        {
          icon: "mdi-widgets",
          text: "Gyms",
          route: "/home/gyms",
        },
      ],
      //   lodUserId: window.localStorage.getItem("idUser"),
      idGym: window.localStorage.getItem("idGymU"),
    };
  },

  async created() {
    // this.idGym = this.$route.params.idGym;
    // alert(this.$route.params.idGym);
    try {
      //   alert(this.idGym);
      var result = await axios.get(
        "http://localhost:8081/rating/ratingGym/" + this.idGym
      );
      //   alert(this.idGym);
      this.reviews = result.data;
    } catch (e) {
      alert("Eroare show rating!");
      //   this.$router.push({ name: "home" });
    }
  },

  methods: { 
    
  },

};
</script>

<style lang="scss" scoped>
input {
  border: 2px solid black;
}
</style>


