package com.mrboomdev.awery.extensions.support.yomi.aniyomi;

import androidx.annotation.NonNull;

import com.mrboomdev.awery.extensions.support.template.CatalogMedia;

import eu.kanade.tachiyomi.animesource.model.SAnime;
import eu.kanade.tachiyomi.animesource.model.SAnimeImpl;

public class AniyomiMedia extends CatalogMedia {
	private final SAnime anime;

	public AniyomiMedia(@NonNull AniyomiProvider provider, @NonNull SAnime anime) {
		super(AniyomiManager.TYPE_ID + ";;;"
				+ provider.getName() + ";;;"
				+ anime.getUrl());

		this.setTitle(anime.getTitle());
		this.setPoster(anime.getThumbnail_url());

		this.status = switch(anime.getStatus()) {
			case SAnime.COMPLETED, SAnime.PUBLISHING_FINISHED -> CatalogMedia.MediaStatus.COMPLETED;
			case SAnime.ONGOING -> CatalogMedia.MediaStatus.ONGOING;
			case SAnime.ON_HIATUS -> CatalogMedia.MediaStatus.PAUSED;
			case SAnime.CANCELLED -> CatalogMedia.MediaStatus.CANCELLED;
			default -> CatalogMedia.MediaStatus.UNKNOWN;
		};

		this.url = anime.getUrl();
		this.genres = anime.getGenres();
		this.description = anime.getDescription();
		this.anime = anime;
	}

	protected static SAnime fromMedia(CatalogMedia media) {
		if(media instanceof AniyomiMedia animeMedia) {
			return animeMedia.getAnime();
		}

		var anime = new SAnimeImpl();
		anime.setTitle(media.title);
		anime.setUrl(media.url);
		anime.setDescription(media.description);
		anime.setThumbnail_url(media.poster.extraLarge);

		return anime;
	}

	protected SAnime getAnime() {
		return anime;
	}
}