<template>
  <div>
    <h1>Range</h1>
    <p>SuperFrog request is only available within 50 miles of campus</p>
    <div id="map" class="map"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      googleMapsApiKey: 'AIzaSyAAwBPecFmf0co-0ua1pg2tA4wXMfIn3R4',
    };
  },
  methods: {
    initMap() {
      // Create a new map instance
      const map = new google.maps.Map(document.getElementById('map'), {
        center: { lat: 32.709531, lng: -97.362856 }, 
        zoom: 15,
      });
    },
    
    loadGoogleMapsScript() {
      // Check if the Google Maps script is already loaded
      if (!window.google) {
        var script = document.createElement('script');
        script.src = `https://maps.googleapis.com/maps/api/js?key=${this.googleMapsApiKey}&callback=initMap`;
        script.async = true;
        script.defer = true;
        // Attach your callback function to the `window` object
        window.initMap = this.initMap.bind(this);
        document.head.appendChild(script);
      } else {
        // If already loaded, just initialize the map
        this.initMap();
      }
    }
  },
  mounted() {
    this.loadGoogleMapsScript();
  },
}
</script>

<style lang="scss" scoped>
  h1, p{
    margin-top: 10px;
    margin-left: 50px;
    color: white;
  }
  .map {
    height: 500px;
    width: 500px; /* Set to 100% to fill the container, or set a specific width */
    margin: 0 auto; /* This centers the map in the container */
  }
</style>
